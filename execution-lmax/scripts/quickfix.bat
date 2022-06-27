@echo off
set file=quickfixj-ex-client.cfg
echo This will configure %file%
echo Check if file exist...

set home=%cd%
echo "Home: %home%"

copy %home%\scripts\%file%.base %home%\src\main\resources\%file%

set FIX_SENDER_COMP_ID
set FIX_TARGET_COMP_ID
echo SenderCompID=%FIX_SENDER_COMP_ID% >> %home%\src\main\resources\%file%