package services;

import entity.MemberDO;
import req.TableDTO;
import req.MemberRequest;

/**
 * @author Xiang Weng
 */
public interface MemberService {

    TableDTO retrieveMembers(MemberRequest request);

    boolean add(MemberDO memberDO);

    MemberDO getByID(int selectedMemberID);

    boolean update(MemberDO memberDO);

    boolean delete(int[] selectedMemberIDs);
}
