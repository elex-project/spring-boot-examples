package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			.of(Locale.ENGLISH, Locale.KOREAN);

	private Locale locale = null;
	private final AcceptHeaderLocaleResolver acceptHeaderLocaleResolver;

	public CustomLocaleResolver() {
		super();

		acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setSupportedLocales(supportedLocales);
		acceptHeaderLocaleResolver.setDefaultLocale(supportedLocales.get(0));
	}

	@Override
	public @NotNull Locale resolveLocale(@NotNull HttpServletRequest request) {
		if (null == locale) {
			return acceptHeaderLocaleResolver.resolveLocale(request);
		} else {
			for (Locale loc : supportedLocales) {
				if (locale.equals(loc)) return loc;
			}
			for (Locale loc : supportedLocales) {
				if (locale.getLanguage().equals(loc.getLanguage())) return loc;
			}
			return supportedLocales.get(0);
		}
	}

	/**
	 * 안터셉터에서 처리될껄?
	 * @param request
	 * @param response
	 * @param locale
	 */
	@Override
	public void setLocale(@NotNull HttpServletRequest request, HttpServletResponse response,
	                      Locale locale) {
		this.locale = locale;
	}
}
