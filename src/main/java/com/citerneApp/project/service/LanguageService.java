package com.citerneApp.project.service;

import com.citerneApp.project.model.Language;
import java.util.List;

public interface LanguageService {

    List<Language> getLanguages();

    Language getLanguage(Long id);

    Language getLanguageByPrefix(String code);

}
