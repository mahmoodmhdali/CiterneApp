package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.Blacklist;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BlacklistService {

    /**
     *
     * @return all blacklist
     */
    List<Blacklist> getBlacklists(String source);

    /**
     *
     * @param blacklist {@link com.citerneApp.MCAAPI.model.Blacklist} object with
     * the mandatory parameters
     * @return {@link com.citerneApp.MCAAPI.helpermodel.ResponseBodyEntity} holding
     * the code, description and data
     */
    ResponseBodyEntity addBlacklist(Blacklist blacklist, String Source);

    ResponseBodyEntity deleteBlacklist(Long id);

    ResponseBodyEntity deleteBlacklist(String msisdn, String Source);

    public Object[] store(MultipartFile file);

    public Object[] delete(MultipartFile file);

    Blacklist getBlacklistByMSISDN(String MSISDN);

}
