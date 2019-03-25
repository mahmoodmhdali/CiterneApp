package com.citerneApp.project.dao;

import com.citerneApp.project.model.Language;
import java.util.List;

public interface LanguageDao {

    List<Language> getLanguages();

    Language getLanguage(Long id);

    Language getLanguageByPrefix(String code);
}
