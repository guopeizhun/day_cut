package com.letg.day_cut.service;

import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.CommonLoginVO;

public interface LoginService {
    Result common(CommonLoginVO loginVO);
}
