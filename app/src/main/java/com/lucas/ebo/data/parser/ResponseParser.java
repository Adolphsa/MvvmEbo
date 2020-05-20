package com.lucas.ebo.data.parser;

import com.lucas.ebo.data.http.EboException;
import com.lucas.ebo.ui.base.BaseResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

/**
 * Created by lucas
 * Date: 2020/5/18 16:49
 *
 * 输入T,输出T,并对code统一判断
 */
@Parser(name = "Response", wrappers = {List.class, PageList.class})
public class ResponseParser<T> extends AbstractParser<T> {

    /**
     * 此构造方法适用于任意Class对象，但更多用于带泛型的Class对象，如：List<Student>
     * <p>
     * 用法:
     * Java: .asParser(new ResponseParser<List<Student>>(){})
     * <p>
     * 注：此构造方法一定要用protected关键字修饰，否则调用此构造方法将拿不到泛型类型
     */
    protected ResponseParser() {
        super();
    }

    /**
     * 此构造方法仅适用于不带泛型的Class对象，如: Student.class
     * <p>
     * 用法
     * Java: .asParser(new ResponseParser<>(Student.class))   或者  .asResponse(Student.class)
     */
    public ResponseParser(Type type) {
        super(type);
    }

    @Override
    public T onParse(@NotNull Response response) throws IOException {

        final Type type = ParameterizedTypeImpl.get(BaseResponse.class, mType);
        BaseResponse<T> data = convert(response, type);
        T t = data.getData();
        if (t == null && mType == String.class)
        {
            /*
             * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
             * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
             * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
             */
            t = (T)data.getMsg();
        }

        if (data.getCode() != CodeRule.CODE_193100 || t == null)
        {
//            throw new ParseException(String.valueOf(data.getCode()), data.getMsg(), response);
            throw new EboException(data.getCode(), data.getMsg());
        }


        return t;
    }




    public static final class CodeRule {
        //请求成功, 正确的操作方式
        static final int CODE_193100 = 193100;

//        //验证码错误
//        static final int CODE_193112 = 193112;
//
//        //验证码过期
//        static final int CODE_193114 = 193114;
//
//        //手机号/邮箱/昵称已被注册
//        static final int CODE_193106 = 193106;
//
//        //短信请求发送太频繁
//        static final int CODE_193107 = 193107;
//
//        //邮件验证请求发送太频繁
//        static final int CODE_193108 = 193108;
//
//        //两种类型的消息发送间隔均为1分中
//        static final int CODE_193115 = 193115;

    }
}
