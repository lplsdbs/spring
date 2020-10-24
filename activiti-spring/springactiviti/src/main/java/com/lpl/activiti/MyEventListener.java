package com.lpl.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

import static org.activiti.engine.delegate.event.ActivitiEventType.JOB_EXECUTION_FAILURE;
import static org.activiti.engine.delegate.event.ActivitiEventType.JOB_EXECUTION_SUCCESS;
//Activiti 5.15中实现了一种事件机制。它允许在引擎触发事件时获得提醒。
public class MyEventListener implements ActivitiEventListener {

    @Override
    public void onEvent(ActivitiEvent event) {
        switch (event.getType()) {

            case JOB_EXECUTION_SUCCESS:
                System.out.println("A job well done!");
                break;

            case JOB_EXECUTION_FAILURE:
                System.out.println("A job has failed...");
                break;

            default:
                System.out.println("Event received: " + event.getType());
        }
    }

    @Override
    public boolean isFailOnException() {
        // The logic in the onEvent method of this listener is not critical, exceptions
        // can be ignored if logging fails...
        return false;
    }
}
