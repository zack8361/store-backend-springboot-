package UsedStore.Core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private static Locale getLocale() { return Locale.KOREA; }

    public static String getMessage(String code) {
        try{
            return messageSource.getMessage(code, null, getLocale());
        }catch (Exception e){
            return code + " messageSource can't find";
        }
    }
}
