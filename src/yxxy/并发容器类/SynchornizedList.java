package yxxy.并发容器类;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchornizedList {
    List<String> strs = new ArrayList<>();
    List<String> strsSync = Collections.synchronizedList(strs);
    //普通list 传入  Collections.synchronizedList(strs) 中 返回的就是 一个加了锁的list
}
