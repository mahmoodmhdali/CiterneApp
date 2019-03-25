package com.citerneApp.project.service;

import com.citerneApp.project.dao.LanguageDao;
import com.citerneApp.project.model.Language;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("languageService")
@Transactional
public class LanguageServiceImpl extends AbstractService implements LanguageService {

    @Autowired
    @Qualifier("languageDao")
    LanguageDao languageDao;

    @Override
    public List<Language> getLanguages() {
        return languageDao.getLanguages();
    }

    @Override
    public Language getLanguage(Long id) {
        return languageDao.getLanguage(id);
    }

    @Override
    public Language getLanguageByPrefix(String code) {
        return languageDao.getLanguageByPrefix(code);
    }

}
