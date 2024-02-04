import english from "./en.json";
import spanish from "./es.json";

const LANG = {
  ENGLISH: "en",
  SPANISH: "es",
};

const getI18N = ({ currentLocale = "es" }) => {
  if (currentLocale === LANG.SPANISH) return { ...spanish };
  if (currentLocale === LANG.ENGLISH) return { ...english };
  return spanish;
};

export { LANG, getI18N };
