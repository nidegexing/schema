package netty.timeserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class TimeServer {

    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 配置服务器的NIO线程租
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            System.out.println("server initChannel..");
            // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
            ch.pipeline().addLast(new HttpResponseEncoder());
            // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
            ch.pipeline().addLast(new HttpRequestDecoder());
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }
//
//    public static void main(String[] args) throws Exception {
//        int port = 9000;
//        if (args != null && args.length > 0) {
//            try {
//                port = Integer.valueOf(args[0]);
//            } catch (NumberFormatException e) {
//
//            }
//        }
//
//        new TimeServer().bind(port);
//    }
    
    public static void main(String[] args) {
		Integer  a = Integer.valueOf(args[0]);
		int b = Integer.valueOf(args[1]).intValue();
		System.out.println(a == b);
		System.out.println(a.equals(b));
	}
}