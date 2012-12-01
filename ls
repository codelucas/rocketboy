[33mcommit 481ac6e2dc311a4eb01294dbe3ddb5b9212c4f01[m
Author: codelucas <lukeprogamming@gmail.com>
Date:   Sat Dec 1 09:55:19 2012 -0800

    initial commit

[1mdiff --git a/.classpath b/.classpath[m
[1mnew file mode 100644[m
[1mindex 0000000..a9a6254[m
[1m--- /dev/null[m
[1m+++ b/.classpath[m
[36m@@ -0,0 +1,7 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<classpath>[m
[32m+[m	[32m<classpathentry kind="src" path="src"/>[m
[32m+[m	[32m<classpathentry kind="src" path="res"/>[m
[32m+[m	[32m<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.6"/>[m
[32m+[m	[32m<classpathentry kind="output" path="bin"/>[m
[32m+[m[32m</classpath>[m
[1mdiff --git a/.project b/.project[m
[1mnew file mode 100644[m
[1mindex 0000000..a7b2ba8[m
[1m--- /dev/null[m
[1m+++ b/.project[m
[36m@@ -0,0 +1,17 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<projectDescription>[m
[32m+[m	[32m<name>RocketBoy</name>[m
[32m+[m	[32m<comment></comment>[m
[32m+[m	[32m<projects>[m
[32m+[m	[32m</projects>[m
[32m+[m	[32m<buildSpec>[m
[32m+[m		[32m<buildCommand>[m
[32m+[m			[32m<name>org.eclipse.jdt.core.javabuilder</name>[m
[32m+[m			[32m<arguments>[m
[32m+[m			[32m</arguments>[m
[32m+[m		[32m</buildCommand>[m
[32m+[m	[32m</buildSpec>[m
[32m+[m	[32m<natures>[m
[32m+[m		[32m<nature>org.eclipse.jdt.core.javanature</nature>[m
[32m+[m	[32m</natures>[m
[32m+[m[32m</projectDescription>[m
[1mdiff --git a/.settings/org.eclipse.jdt.core.prefs b/.settings/org.eclipse.jdt.core.prefs[m
[1mnew file mode 100644[m
[1mindex 0000000..8000cd6[m
[1m--- /dev/null[m
[1m+++ b/.settings/org.eclipse.jdt.core.prefs[m
[36m@@ -0,0 +1,11 @@[m
[32m+[m[32meclipse.preferences.version=1[m
[32m+[m[32morg.eclipse.jdt.core.compiler.codegen.inlineJsrBytecode=enabled[m
[32m+[m[32morg.eclipse.jdt.core.compiler.codegen.targetPlatform=1.6[m
[32m+[m[32morg.eclipse.jdt.core.compiler.codegen.unusedLocal=preserve[m
[32m+[m[32morg.eclipse.jdt.core.compiler.compliance=1.6[m
[32m+[m[32morg.eclipse.jdt.core.compiler.debug.lineNumber=generate[m
[32m+[m[32morg.eclipse.jdt.core.compiler.debug.localVariable=generate[m
[32m+[m[32morg.eclipse.jdt.core.compiler.debug.sourceFile=generate[m
[32m+[m[32morg.eclipse.jdt.core.compiler.problem.assertIdentifier=error[m
[32m+[m[32morg.eclipse.jdt.core.compiler.problem.enumIdentifier=error[m
[32m+[m[32morg.eclipse.jdt.core.compiler.source=1.6[m
[1mdiff --git a/bin/Barn.gif b/bin/Barn.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..f5a3ca5[m
Binary files /dev/null and b/bin/Barn.gif differ
[1mdiff --git a/bin/CloudFour.gif b/bin/CloudFour.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..b7cdac7[m
Binary files /dev/null and b/bin/CloudFour.gif differ
[1mdiff --git a/bin/CloudOne.gif b/bin/CloudOne.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..c515324[m
Binary files /dev/null and b/bin/CloudOne.gif differ
[1mdiff --git a/bin/CloudThree.gif b/bin/CloudThree.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..33a4e76[m
Binary files /dev/null and b/bin/CloudThree.gif differ
[1mdiff --git a/bin/CloudTwo.gif b/bin/CloudTwo.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..1895b80[m
Binary files /dev/null and b/bin/CloudTwo.gif differ
[1mdiff --git a/bin/Deathscreen.jpg b/bin/Deathscreen.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..902b448[m
Binary files /dev/null and b/bin/Deathscreen.jpg differ
[1mdiff --git a/bin/Grass.gif b/bin/Grass.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..62538ea[m
Binary files /dev/null and b/bin/Grass.gif differ
[1mdiff --git a/bin/HIGHSCORES.txt b/bin/HIGHSCORES.txt[m
[1mnew file mode 100644[m
[1mindex 0000000..24b2b19[m
[1m--- /dev/null[m
[1m+++ b/bin/HIGHSCORES.txt[m
[36m@@ -0,0 +1 @@[m
[32m+[m[32mluke 999999999 1[m
\ No newline at end of file[m
[1mdiff --git a/bin/Highscores.png b/bin/Highscores.png[m
[1mnew file mode 100644[m
[1mindex 0000000..28977ab[m
Binary files /dev/null and b/bin/Highscores.png differ
[1mdiff --git a/bin/HomeButton.gif b/bin/HomeButton.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..7be1c59[m
Binary files /dev/null and b/bin/HomeButton.gif differ
[1mdiff --git a/bin/Instructions.jpg b/bin/Instructions.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..abff174[m
Binary files /dev/null and b/bin/Instructions.jpg differ
[1mdiff --git a/bin/IntroPic.jpg b/bin/IntroPic.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..b726617[m
Binary files /dev/null and b/bin/IntroPic.jpg differ
[1mdiff --git a/bin/QuestionUpgrade.gif b/bin/QuestionUpgrade.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..0b9dbbd[m
Binary files /dev/null and b/bin/QuestionUpgrade.gif differ
[1mdiff --git a/bin/RocketBoyLogo.png b/bin/RocketBoyLogo.png[m
[1mnew file mode 100644[m
[1mindex 0000000..46fb93c[m
Binary files /dev/null and b/bin/RocketBoyLogo.png differ
[1mdiff --git a/bin/Sky.gif b/bin/Sky.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..695b512[m
Binary files /dev/null and b/bin/Sky.gif differ
[1mdiff --git a/bin/Sound/SorairoDays.mid b/bin/Sound/SorairoDays.mid[m
[1mnew file mode 100644[m
[1mindex 0000000..410fd80[m
Binary files /dev/null and b/bin/Sound/SorairoDays.mid differ
[1mdiff --git a/bin/Sound/airHorn.wav b/bin/Sound/airHorn.wav[m
[1mnew file mode 100644[m
[1mindex 0000000..63b2ba0[m
Binary files /dev/null and b/bin/Sound/airHorn.wav differ
[1mdiff --git a/bin/Sound/beep.wav b/bin/Sound/beep.wav[m
[1mnew file mode 100644[m
[1mindex 0000000..9f643b4[m
Binary files /dev/null and b/bin/Sound/beep.wav differ
[1mdiff --git a/bin/Sound/desktop.ini b/bin/Sound/desktop.ini[m
[1mnew file mode 100644[m
[1mindex 0000000..fa71510[m
[1m--- /dev/null[m
[1m+++ b/bin/Sound/desktop.ini[m
[36m@@ -0,0 +1,3 @@[m
[32m+[m[32m[LocalizedFileNames][m
[32m+[m[32mWindows Battery Critical.wav=@%windir%\system32\mmsys.cpl,-712[m
[32m+[m[32mWindows Logoff Sound.wav=@%windir%\system32\mmsys.cpl,-723[m
[1mdiff --git a/bin/Sound/inflate.wav b/bin/Sound/inflate.wav[m
[1mnew file mode 100644[m
[1mindex 0000000..9df22bc[m
Binary files /dev/null and b/bin/Sound/inflate.wav differ
[1mdiff --git a/bin/Sound/jab.wav b/bin/Sound/jab.wav[m
[1mnew file mode 100644[m
[1mindex 0000000..6e6ca93[m
Binary files /dev/null and b/bin/Sound/jab.wav differ
[1mdiff --git a/bin/Sound/jetpackIgnite.wav b/bin/Sound/jetpackIgnite.wav[m
[1mnew file mode 100644[m
[1mindex 0000000..890b8bd[m
Binary files /dev/null and b/bin/Sound/jetpackIgnite.wav differ
[1mdiff --git a/bin/Sound/missileSound.wav b/bin/Sound/missileSound.wav[m
[1mnew file mode 100644[m
[1mindex 0000000..a9b9fdb[m
Binary files /dev/null and b/bin/Sound/missileSound.wav differ
[1mdiff --git a/bin/Sound/presenceInTheAir.mid b/bin/Sound/presenceInTheAir.mid[m
[1mnew file mode 100644[m
[1mindex 0000000..ee2a599[m
Binary files /dev/null and b/bin/Sound/presenceInTheAir.mid differ
[1mdiff --git a/bin/Sound/splat.WAV b/bin/Sound/splat.WAV[m
[1mnew file mode 100644[m
[1mindex 0000000..9f62325[m
Binary files /dev/null and b/bin/Sound/splat.WAV differ
[1mdiff --git a/bin/Spike.gif b/bin/Spike.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..2f16740[m
Binary files /dev/null and b/bin/Spike.gif differ
[1mdiff --git a/bin/Tree.gif b/bin/Tree.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..b89a977[m
Binary files /dev/null and b/bin/Tree.gif differ
[1mdiff --git a/bin/balloonCrate.png b/bin/balloonCrate.png[m
[1mnew file mode 100644[m
[1mindex 0000000..349c690[m
Binary files /dev/null and b/bin/balloonCrate.png differ
[1mdiff --git a/bin/charDead.png b/bin/charDead.png[m
[1mnew file mode 100644[m
[1mindex 0000000..cab2f5d[m
Binary files /dev/null and b/bin/charDead.png differ
[1mdiff --git a/bin/flameFour.gif b/bin/flameFour.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..01d2ff1[m
Binary files /dev/null and b/bin/flameFour.gif differ
[1mdiff --git a/bin/flameOne.gif b/bin/flameOne.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..819d00f[m
Binary files /dev/null and b/bin/flameOne.gif differ
[1mdiff --git a/bin/flameThree.gif b/bin/flameThree.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..238790a[m
Binary files /dev/null and b/bin/flameThree.gif differ
[1mdiff --git a/bin/flameTwo.gif b/bin/flameTwo.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..7945d1e[m
Binary files /dev/null and b/bin/flameTwo.gif differ
[1mdiff --git a/bin/gasPumpFilled.gif b/bin/gasPumpFilled.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..392e004[m
Binary files /dev/null and b/bin/gasPumpFilled.gif differ
[1mdiff --git a/bin/jetpackLeft.gif b/bin/jetpackLeft.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..00d0316[m
Binary files /dev/null and b/bin/jetpackLeft.gif differ
[1mdiff --git a/bin/jetpackRight.gif b/bin/jetpackRight.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..3c498ae[m
Binary files /dev/null and b/bin/jetpackRight.gif differ
[1mdiff --git a/bin/leftBirdOne.png b/bin/leftBirdOne.png[m
[1mnew file mode 100644[m
[1mindex 0000000..cfa59b1[m
Binary files /dev/null and b/bin/leftBirdOne.png differ
[1mdiff --git a/bin/leftBirdTwo.png b/bin/leftBirdTwo.png[m
[1mnew file mode 100644[m
[1mindex 0000000..69e9e70[m
Binary files /dev/null and b/bin/leftBirdTwo.png differ
[1mdiff --git a/bin/leftFour.png b/bin/leftFour.png[m
[1mnew file mode 100644[m
[1mindex 0000000..17f885e[m
Binary files /dev/null and b/bin/leftFour.png differ
[1mdiff --git a/bin/leftOne.png b/bin/leftOne.png[m
[1mnew file mode 100644[m
[1mindex 0000000..2fbeb98[m
Binary files /dev/null and b/bin/leftOne.png differ
[1mdiff --git a/bin/leftThree.png b/bin/leftThree.png[m
[1mnew file mode 100644[m
[1mindex 0000000..4222281[m
Binary files /dev/null and b/bin/leftThree.png differ
[1mdiff --git a/bin/leftTwo.png b/bin/leftTwo.png[m
[1mnew file mode 100644[m
[1mindex 0000000..759eb54[m
Binary files /dev/null and b/bin/leftTwo.png differ
[1mdiff --git a/bin/leftUp.gif b/bin/leftUp.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..6e527ac[m
Binary files /dev/null and b/bin/leftUp.gif differ
[1mdiff --git a/bin/lucasAuthorText.png b/bin/lucasAuthorText.png[m
[1mnew file mode 100644[m
[1mindex 0000000..0764c44[m
Binary files /dev/null and b/bin/lucasAuthorText.png differ
[1mdiff --git a/bin/missile sprite sheet.png b/bin/missile sprite sheet.png[m
[1mnew file mode 100644[m
[1mindex 0000000..4e1dd8f[m
Binary files /dev/null and b/bin/missile sprite sheet.png differ
[1mdiff --git a/bin/missileCloseWarning.png b/bin/missileCloseWarning.png[m
[1mnew file mode 100644[m
[1mindex 0000000..eb100d1[m
Binary files /dev/null and b/bin/missileCloseWarning.png differ
[1mdiff --git a/bin/missileFive.png b/bin/missileFive.png[m
[1mnew file mode 100644[m
[1mindex 0000000..7681054[m
Binary files /dev/null and b/bin/missileFive.png differ
[1mdiff --git a/bin/missileFour.png b/bin/missileFour.png[m
[1mnew file mode 100644[m
[1mindex 0000000..1a298fc[m
Binary files /dev/null and b/bin/missileFour.png differ
[1mdiff --git a/bin/missileOne.png b/bin/missileOne.png[m
[1mnew file mode 100644[m
[1mindex 0000000..8b6c243[m
Binary files /dev/null and b/bin/missileOne.png differ
[1mdiff --git a/bin/missileThree.png b/bin/missileThree.png[m
[1mnew file mode 100644[m
[1mindex 0000000..2d3a7ac[m
Binary files /dev/null and b/bin/missileThree.png differ
[1mdiff --git a/bin/missileTwo.png b/bin/missileTwo.png[m
[1mnew file mode 100644[m
[1mindex 0000000..07ee472[m
Binary files /dev/null and b/bin/missileTwo.png differ
[1mdiff --git a/bin/missileWarning.png b/bin/missileWarning.png[m
[1mnew file mode 100644[m
[1mindex 0000000..d1df859[m
Binary files /dev/null and b/bin/missileWarning.png differ
[1mdiff --git a/bin/platformOne.png b/bin/platformOne.png[m
[1mnew file mode 100644[m
[1mindex 0000000..ff78d58[m
Binary files /dev/null and b/bin/platformOne.png differ
[1mdiff --git a/bin/platformSpike.png b/bin/platformSpike.png[m
[1mnew file mode 100644[m
[1mindex 0000000..25c3f8c[m
Binary files /dev/null and b/bin/platformSpike.png differ
[1mdiff --git a/bin/rightBirdOne.png b/bin/rightBirdOne.png[m
[1mnew file mode 100644[m
[1mindex 0000000..b3f2f77[m
Binary files /dev/null and b/bin/rightBirdOne.png differ
[1mdiff --git a/bin/rightBirdTwo.png b/bin/rightBirdTwo.png[m
[1mnew file mode 100644[m
[1mindex 0000000..41731d6[m
Binary files /dev/null and b/bin/rightBirdTwo.png differ
[1mdiff --git a/bin/rightFour.png b/bin/rightFour.png[m
[1mnew file mode 100644[m
[1mindex 0000000..cc37fc3[m
Binary files /dev/null and b/bin/rightFour.png differ
[1mdiff --git a/bin/rightOne.png b/bin/rightOne.png[m
[1mnew file mode 100644[m
[1mindex 0000000..c8222a6[m
Binary files /dev/null and b/bin/rightOne.png differ
[1mdiff --git a/bin/rightThree.png b/bin/rightThree.png[m
[1mnew file mode 100644[m
[1mindex 0000000..8de445c[m
Binary files /dev/null and b/bin/rightThree.png differ
[1mdiff --git a/bin/rightTwo.png b/bin/rightTwo.png[m
[1mnew file mode 100644[m
[1mindex 0000000..1b5393f[m
Binary files /dev/null and b/bin/rightTwo.png differ
[1mdiff --git a/bin/rightUp.gif b/bin/rightUp.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..1a5631f[m
Binary files /dev/null and b/bin/rightUp.gif differ
[1mdiff --git a/bin/skullCounter.png b/bin/skullCounter.png[m
[1mnew file mode 100644[m
[1mindex 0000000..6354545[m
Binary files /dev/null and b/bin/skullCounter.png differ
[1mdiff --git a/bin/volumeOff.png b/bin/volumeOff.png[m
[1mnew file mode 100644[m
[1mindex 0000000..31560f3[m
Binary files /dev/null and b/bin/volumeOff.png differ
[1mdiff --git a/bin/volumeOn.png b/bin/volumeOn.png[m
[1mnew file mode 100644[m
[1mindex 0000000..2a3c6e6[m
Binary files /dev/null and b/bin/volumeOn.png differ
[1mdiff --git a/res/Barn.gif b/res/Barn.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..f5a3ca5[m
Binary files /dev/null and b/res/Barn.gif differ
[1mdiff --git a/res/CloudFour.gif b/res/CloudFour.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..b7cdac7[m
Binary files /dev/null and b/res/CloudFour.gif differ
[1mdiff --git a/res/CloudOne.gif b/res/CloudOne.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..c515324[m
Binary files /dev/null and b/res/CloudOne.gif differ
[1mdiff --git a/res/CloudThree.gif b/res/CloudThree.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..33a4e76[m
Binary files /dev/null and b/res/CloudThree.gif differ
[1mdiff --git a/res/CloudTwo.gif b/res/CloudTwo.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..1895b80[m
Binary files /dev/null and b/res/CloudTwo.gif differ
[1mdiff --git a/res/Deathscreen.jpg b/res/Deathscreen.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..902b448[m
Binary files /dev/null and b/res/Deathscreen.jpg differ
[1mdiff --git a/res/Grass.gif b/res/Grass.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..62538ea[m
Binary files /dev/null and b/res/Grass.gif differ
[1mdiff --git a/res/HIGHSCORES.txt b/res/HIGHSCORES.txt[m
[1mnew file mode 100644[m
[1mindex 0000000..24b2b19[m
[1m--- /dev/null[m
[1m+++ b/res/HIGHSCORES.txt[m
[36m@@ -0,0 +1 @@[m
[32m+[m[32mluke 999999999 1[m
\ No newline at end of file[m
[1mdiff --git a/res/Highscores.png b/res/Highscores.png[m
[1mnew file mode 100644[m
[1mindex 0000000..28977ab[m
Binary files /dev/null and b/res/Highscores.png differ
[1mdiff --git a/res/HomeButton.gif b/res/HomeButton.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..7be1c59[m
Binary files /dev/null and b/res/HomeButton.gif differ
[1mdiff --git a/res/Instructions.jpg b/res/Instructions.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..abff174[m
Binary files /dev/null and b/res/Instructions.jpg differ
[1mdiff --git a/res/IntroPic.jpg b/res/IntroPic.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..b726617[m
Binary files /dev/null and b/res/IntroPic.jpg differ
[1mdiff --git a/res/QuestionUpgrade.gif b/res/QuestionUpgrade.gif[m
[1mnew file mode 100644[m
[1mindex 0000000..0b9dbbd[m
Binary file