package voice_test.service.Imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import voice.voice_register_client.service.RegisterSVC;
import voice_test.dao.UserInfoMapper;
import voice_test.po.UserInfo;
import voice_test.po.UserInfoExample;
import voice_test.po.UserInfoExample.Criteria;

@Service("registerService")
public class RegisterSVCImp implements RegisterSVC {

	@Resource
	UserInfoMapper userInfoMapper;

	@Override
	public boolean register(String userName, String password) {
		UserInfoExample example = new UserInfoExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserNameEqualTo(userName);

		List<UserInfo> userInfoList = userInfoMapper.selectByExample(example);
		if (!userInfoList.isEmpty()) {
			return false;
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setUserPassword(password);
		int insert = userInfoMapper.insertSelective(userInfo);
		if (insert!=1) {
			return false;
		}
		
		return true;
	}

}
