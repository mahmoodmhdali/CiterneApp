package com.citerneApp.project.dao;

import com.citerneApp.project.model.PagesLabels;
import java.util.List;

/**
 *
 * @author cabdelsater
 */
public interface PagesLabelsDao {

    List<PagesLabels> getLabels(Long pageid, Long labelid, Long langid);
}
