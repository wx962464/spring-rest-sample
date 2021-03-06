package vip.maxhub.web.sample.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest错误回复的定义
 * Created by jinlei on 2017/4/19.
 */
public class ErrorResponse {

    @JsonProperty(value = "error_code")
    private final int errorCode;

    @JsonProperty(value = "message")
    private final String message;

    public ErrorResponse(RestException ex){
        String code = ex.getHttpStatus().toString() + ex.getReasonCode();
        this.errorCode = Integer.parseInt(code);
        this.message = ex.getLocalizedMessage();
    }

    /**
     * 获取最终错误码，返回给客户端时，展现此码。
     * <p>
     * errorCode的编码规则是根据HTTP状态码 + 具体业务错误说明编码。
     * <p>
     * 客户端请求错误: 4XX + YYY。XX, 参考HTTP协议规定的状态码，YYY，是具体业务错误说明编码。
     * 如: 400100， 表示不能识别的错误。400，是请求参数错误，但系统未能识别该错误编码。
     * <p>
     * 服务器内部错误: 5XX + YYY。
     * <p>
     * 错误码的前三位数字(4XX,5XX)，保持与返回的HTTP status一致。
     * 错误的的后三位数字(YYY)，用于具体错误的识别。
     * <p>
     * @return
     */
    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "errorCode=" + errorCode +
            ", message='" + message + '\'' +
            '}';
    }
}
