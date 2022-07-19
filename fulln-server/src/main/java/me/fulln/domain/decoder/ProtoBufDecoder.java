package me.fulln.domain.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.fulln.infrastructure.dto.pb.CommonMessage;

import java.util.List;
import java.util.Objects;

/**
 * @author fulln
 * @description pb decoder
 * @date  Created in  14:26  2022/7/19.
 **/
public class ProtoBufDecoder extends ByteToMessageDecoder implements AbsDecoder{

    /**
     * Decode the from one {@link ByteBuf} to an other. This method will be called till either the input
     * {@link ByteBuf} has nothing to read when return from this method or till nothing was read from the input
     * {@link ByteBuf}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in  the {@link ByteBuf} from which to read data
     * @param out the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 标记读指针的位置
        in.markReaderIndex();
        // 判断头部长度 小于2
        if (in.readableBytes() < 1 <<1){
            return;
        }
        // 获取对应长度
        short i = in.readShort();
        if (i < 0){
            // 异常直接关闭当前通道
            ctx.close();
        }

        if (i > in.readableBytes()){
            // 如果长度小于了传送过来的长度
            in.resetReaderIndex();
            return;
        }
        byte[] array;
        if (in.hasArray()){
            ByteBuf buf = in.slice();
            array = buf.array();
        }else{
            array =  new byte[i];
            in.readBytes(array,0,i);
        }
        CommonMessage.Msg  msg =  CommonMessage.Msg.parseFrom(array);
        if (Objects.nonNull(msg)){
            out.add(msg);
        }
    }
}
