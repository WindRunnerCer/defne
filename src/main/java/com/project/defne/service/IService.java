package com.project.defne.service;

/**
 * Created by Ceren A. @ 5/9/2019
 * While listening ${SPOT}
 */

public interface IService<RQ,RS> {
    public RS execute(RQ request);
}
