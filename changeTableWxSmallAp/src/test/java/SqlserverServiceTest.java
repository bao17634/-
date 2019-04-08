import com.qm.volkswagenChina.Application;
import com.qm.volkswagenChina.partsTransaction.smallUser.entity.SmallUser;
import com.qm.volkswagenChina.partsTransaction.smallUser.service.SmallUserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SqlserverServiceTest  {
    @Autowired
    SmallUserService smallUserService;
    @Autowired
    CacheManager cacheManager;
//    @Test
//    public void testSmallUser(){
//        SmallUser user = new SmallUser();
//        user.setOpenId("0000000000000000000");
//        user.setCity("长春");
//        user.setProvince("吉林省");
//        user.setNickName("古刹飞鹰");
//        smallUserService.save(user);
//        SmallUser u = smallUserService.findByOpenId("0000000000000000000");
//        Assert.assertEquals(u.getOpenId(),"0000000000000000000");
//        Assert.assertEquals(u.getCity(),"长春");
//        smallUserService.deleteByOpenId(u.getOpenId());
//    }
    @Test
    public void testCache(){
        Cache cache = cacheManager.getCache("sessionCache");
        cache.put("abcdefg","古刹飞鹰");
        Assert.assertEquals(cache.get("abcdefg").get().toString(),"古刹飞鹰");
    }
}
