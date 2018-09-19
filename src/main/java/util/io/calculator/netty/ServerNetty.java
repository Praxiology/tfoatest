package util.io.calculator.netty;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerNetty {

    private int port;

    public ServerNetty(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup , workerGroup)
                    //我要指定使用NioServerSocketChannel这种类型的通道
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG , 1) //设置tcp列队缓冲区
                    .option(ChannelOption.SO_SNDBUF , 1)  //设置发送缓冲大小
                    .option(ChannelOption.SO_RCVBUF , 1)  //这是接收缓冲大小
                    .childOption(ChannelOption.SO_KEEPALIVE , true) //保持连接
                    //一定要使用 childHandler 去绑定具体的 事件处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务器开启："+port);
            f.channel().closeFuture().sync();
            System.out.println("over ?");
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 12345;
        }
        new ServerNetty(port).run();
    }
}