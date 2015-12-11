package com.guoguang.dksq.entity;

import com.guoguang.dksq.database.LoanRecord;

import java.util.List;

/**
 * Created by tk on 2015/11/6.
 */
public class Rs_DraftList {
    List<LoanRecord> DraftList;

    public List<LoanRecord> getDraftList() {
        return DraftList;
    }

    public void setDraftList(List<LoanRecord> draftList) {
        DraftList = draftList;
    }
}
