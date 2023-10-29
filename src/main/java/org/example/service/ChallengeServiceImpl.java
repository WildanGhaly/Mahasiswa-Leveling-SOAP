
package org.example.service;

import javax.jws.WebService;
import javax.jws.WebMethod;

import org.example.model.Challenge;

@WebService
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    @WebMethod
    public Challenge getChallengeById(int id) {
        return new Challenge(id, "Challenge " + id, "Description " + id, "Threshold " + id);
    }
}