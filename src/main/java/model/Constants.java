package model;

/**
 * Класс с различными константами.
 *
 * @author Mikalay
 */
public class Constants {

    /**
     * Путь к папке с шаблонами писем.
     */
    public static final String TEMPLATES_DIR = "templates";

    /**
     * Путь к файлам с лог-файлом лога.
     */
    public static final String LOG_FILE_PATH = "log/log";

    /**
     * Название папки с логом.
     */
    public static final String LOG_DIR_PATH = "log";

    /**
     * Регулярное выражение, описывает часть строки перед написанием переменной
     * из заголовка столбца таблицы.
     */
    final public static String START_VAR_REGEX = "<\\s*var\\s*>\\s*";

    /**
     * Регулярное выражение, описывает часть строки после написания переменной
     * из заголовка столбца таблицы.
     */
    final public static String END_VAR_REGEX = "\\s*<\\s*/\\s*var\\s*>";

    /**
     * Регулярное выражение, описывает шаблон ссылки на почту.
     */
    final public static String MAIL_LINK_TEMPLATE = "<\\s*mailLink\\s*>\\s*([^<^>]*)\\s*<\\s*/\\s*mailLink\\s*>";

    /**
     * Регулярное выражение, описывает HTML - ссылку на почту.
     */
    final public static String MAIL_LINK_HTML = "<a href=\"mailto:$1\">$1</a>";

    /**
     * Регулярное выражение, описывает шаблон HTML - ссылки на сайт.
     */
    final public static String SITE_LINK_TEMPLATE = "<\\s*siteLink\\s*>\\s*([^<^>]*)\\s*<\\s*/\\s*siteLink\\s*>";

    /**
     * Регулярное выражение, описывает HTML - ссылку на сайт.
     */
    final public static String SITE_LINK_HTML = "<a href = \"$1\">" + "$1</a>";

    /**
     * Регулярное выражение, описывает шаблон вставки изображения.
     */
    final public static String IMAGE_TEMPLATE = "<\\s*image\\s*>\\s*([^<^>]*)\\s*<\\s*/\\s*image\\s*>";

    /**
     * Регулярное выражение, описывает HTML - вставку изображения.
     */
    final public static String IMAGE_HTML = "<image>$1</image>";

    /**
     * Регулярное выражение, описывает HTML - новую строку.
     */
    final public static String NEW_LINE_HTML = "<br/>";

    /**
     * Регулярное выражение, описывает начало вставки изображения.
     */
    final public static String START_IMAGE_REGEX = "<\\s*image\\s*>";

    /**
     * Регулярное выражение, описывает конец вставки изображения.
     */
    final public static String END_IMAGE_REGEX = "</image>";

    /**
     * Регулярное выражение для вставки между тегами картинки, нужно для
     * разбиения строки вокруг тегов картинки со включением этих тегов.
     */
    final public static String START_IMAGE_WITH_SPLIT_REGEX = "<forSplit><image><forSplit>";

    /**
     * Регулярное выражение для вставки между тегами картинки, нужно для
     * разбиения строки вокруг тегов картинки со включением этих тегов.
     */
    final public static String END_IMAGE_WITH_SPLIT_REGEX = "<forSplit></image><forSplit>";

    /**
     * Регулярное выражение для разбиения строки между тегами картинки с этими
     * тегами.
     */
    final public static String SPLIT_TAG = "<forSplit>";
}
