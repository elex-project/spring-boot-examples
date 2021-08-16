package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 로케일 리졸버
 * AcceptHeaderLocaleResolver를 기본 정책으로하되, GET 파라미터로 로캐일 변환이 가능하도록 한다.
 * @author Elex
 */
@Slf4j
public class CustomLocaleResolver implements LocaleResolver {

	/**
	 * 지원되는 로캐일 목록. 첫 번째 로캐일은 기본 로캐일로 사용된다.
	 */
	private static final List<Locale> supportedLocales = List
			.of(Locale.KOREAN, Locale.ENGLISH);

	//private Locale locale = null;
	private final AcceptHeaderLocaleResolver acceptHeaderLocaleResolver;

	public CustomLocaleResolver() {
		super();

		acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setSupportedLocales(supportedLocales);
		acceptHeaderLocaleResolver.setDefaultLocale(Locale.ENGLISH);
	}

	@Override
	public @NotNull Locale resolveLocale(@NotNull HttpServletRequest request) {
		String lang = request.getParameter("language");
		if (null == lang) {
			return acceptHeaderLocaleResolver.resolveLocale(request);

		} else {
			Locale loc = new Locale(lang);
			List<Locale.LanguageRange> priorityList = new ArrayList<>();
			priorityList.add(new Locale.LanguageRange(loc.toLanguageTag(), 1.0));
			priorityList.add(new Locale.LanguageRange(loc.getLanguage(), 0.9));
			priorityList.add(new Locale.LanguageRange(Locale.ENGLISH.getLanguage(),0.3));
			//priorityList.add(new Locale.LanguageRange(Locale.ROOT.getLanguage(),0.1));
			log.debug("PriorityList: {}", priorityList);
			Locale pick = Locale.lookup(
					priorityList,
					supportedLocales);
			log.debug("Picked locale: {}", pick);
			return pick;
			/*for (Locale loc : supportedLocales) {
				if (locale.equals(loc)) return loc;
			}
			for (Locale loc : supportedLocales) {
				if (locale.getLanguage().equals(loc.getLanguage())) return loc;
			}
			return supportedLocales.get(0);*/
		}
	}

	/**
	 * 세션 등에 저장하고 싶을 때 사용한다.
	 * @param request
	 * @param response
	 * @param locale
	 */
	@Override
	public void setLocale(@NotNull HttpServletRequest request, HttpServletResponse response,
	                      Locale locale) {
		//this.locale = locale;

	}
}
