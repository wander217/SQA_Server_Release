package com.nhom18.server.controller.term.service;

import com.wander.sqa.controller.term.dto.TermDTO;
import com.wander.sqa.controller.term.dto.TermRequest;
import com.wander.sqa.exception.RegTimeException;
import com.wander.sqa.exception.TermNotFoundException;

public interface TermService {
    TermDTO getLastTerm() throws TermNotFoundException;
    void updateTerm(TermRequest t) throws TermNotFoundException, RegTimeException;
}
