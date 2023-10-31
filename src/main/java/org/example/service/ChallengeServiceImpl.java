
package org.example.service;

import javax.jws.WebService;
import javax.jws.WebMethod;

import org.example.model.Challenge;
import org.example.model.ChallengeDAOImpl;
import org.example.core.Database;

@WebService
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    @WebMethod
    public Challenge getChallengeById(int id) {
        ChallengeDAOImpl challengeDAO = new ChallengeDAOImpl();
        return challengeDAO.findById(id);
    }
}