package me.fulln.study.sentinel.service;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionAppService {

    public void delete(Long id) {
        log.info("delete question id:{}", id);
    }


    @PostConstruct
    public void initFlowRules() {
        // 修改系统规则
        changeSystemRule();
        // 修改降级规则
        changeDegradeRule();
        // 修改限流规则
        changeFlowRule();
    }

    private void changeFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setCount(2);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        // 直接根据qps 去修改限流规则
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    private void changeDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("delete");
        rule.setTimeWindow(5);
        rule.setCount(3);
        // 这个是根据1分钟内异常数的多少来判断是否降级的
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        rules.add(rule);
        // 不同策略设置的count 和timeWindow值 含义还不一样

        // 根据时间窗口的比例来判断是否降级的
        DegradeRule rule2 = new DegradeRule();
        rule2.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
        rule2.setCount(0.5);
        rule2.setTimeWindow(5);
        rules.add(rule2);
        DegradeRuleManager.loadRules(rules);
    }

    private void changeSystemRule() {
        List<SystemRule> list = new ArrayList<>();
        SystemRule rule = new SystemRule();
        rule.setHighestSystemLoad(10);
        list.add(rule);
        // 修改系统规则
        SystemRuleManager.loadRules(list);
    }

}

