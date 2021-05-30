package com.nhom18.server.controller.term.service;

import com.nhom18.server.controller.term.dto.TermDTO;
import com.nhom18.server.controller.term.dto.TermRequest;
import com.nhom18.server.exception.RegTimeException;
import com.nhom18.server.exception.TermNotFoundException;

public interface TermService {
    TermDTO getLastTerm() throws TermNotFoundException;
    void updateTerm(TermRequest t) throws TermNotFoundException, RegTimeException;
}
