package com.example.Rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class myRandomRule extends AbstractLoadBalancerRule {
    public myRandomRule() {
    }

    //每个服务，访问5次，换下一个服务（3个）

    //total = 0 , 如果 total = 5，指向下一个服务节点
    //index = 0，如果total = 5，index++，

    private int total = 0;  //被调用的次数
    private  int curentIndex = 0;   //当前是谁在提供服务

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers(); //获取活着的服务
                List<Server> allList = lb.getAllServers();  //获取全部的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);  //生成区间随机数
//                server = (Server)upList.get(index); //从活着的服务中随机获取一个服务

                //-================================================================
                if(total < 5){
                    server = upList.get(curentIndex);
                    total++;
                }else{
                    total = 0;
                    curentIndex++;
                    if(curentIndex > upList.size()){
                        curentIndex = 0;
                    }
                    server = upList.get(curentIndex);   //从活着的服务中，获取指定的服务来进行操作
                }

                //-===============================================================



                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
