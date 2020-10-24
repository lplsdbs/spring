ps -aux | grep credit-yunnan.jar| awk '{print $2}' | xargs kill
