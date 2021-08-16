package kr.pe.elex.examples;

//import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Locale;

/**
 * Mustache 템플릿에 국제화 문자열을 끼워넣기 위해 사용된다.
 */
@Slf4j
@ControllerAdvice
public class I18nAdvice {
	@Autowired
	private MessageSource messageSource;

	@ModelAttribute("i18n")
	public Mustache.Lambda i18n(Locale locale){
		return (frag, out) -> {
			String body = frag.execute();
			String message = this.messageSource.getMessage(body, new String[]{}, locale);
			log.debug("User Locale: {} -> {}", locale, message);
			out.write(message);
		};
	}
}
