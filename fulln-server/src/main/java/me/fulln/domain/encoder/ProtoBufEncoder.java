package me.fulln.domain.encoder;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.fulln.infrastructure.dto.pb.CommonMessage;

public class ProtoBufEncoder extends MessageToByteEncoder<CommonMessage.Msg> implements AbsEncoder{

    /**
     * Encode a message into a {@link ByteBuf}. This method will be called for each written message that can be handled
     * by this encoder.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link MessageToByteEncoder} belongs to
     * @param msg the message to encode
     * @param out the {@link ByteBuf} into which the encoded message will be written
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, CommonMessage.Msg msg, ByteBuf out) throws Exception {
        byte[] bytes = msg.toByteArray();
        String a[]  = new String[10];
        a[1] = "10";

        int length = bytes.length;
        out.writeShort(length);
        // 开始写对应消息
        out.writeBytes(bytes);
    }


}
