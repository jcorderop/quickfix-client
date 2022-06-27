@echo off
echo This will configure quickfixj-client.cfg
echo Check if file exist...

set home=%cd%
echo "Home: %home%"

copy %home%\scripts\quickfixj-client.cfg.base %home%\src\main\resources\quickfixj-client.cfg

set FIX_SENDER_COMP_ID
set FIX_TARGET_COMP_ID
echo SenderCompID=%FIX_SENDER_COMP_ID% >> %home%\src\main\resources\quickfixj-client.cfg
echo TargetCompID=%FIX_TARGET_COMP_ID% >> %home%\src\main\resources\quickfixj-client.cfg
