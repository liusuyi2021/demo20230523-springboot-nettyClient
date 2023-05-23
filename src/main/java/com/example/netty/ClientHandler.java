package com.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ClientHandler
 * @Description:
 * @Author 刘苏义
 * @Date 2023/5/23 21:27
 * @Version 1.0
 */

public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接到服务器时触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
          //  ctx.write(Unpooled.copiedBuffer("current time", CharsetUtil.UTF_8));
            //--------ClientHandler---------
            ctx.write(Unpooled.copiedBuffer("current time\n", CharsetUtil.UTF_8));
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //--------ServerHandler---------
            String callback = sf.format(new Date()) + "\n";
        }
        ctx.flush();
       // ctx.writeAndFlush(Unpooled.copiedBuffer("current time", CharsetUtil.UTF_8));
    }

    /**
     * 消息到来时触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Current Time: " + buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}