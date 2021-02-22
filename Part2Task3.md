## Refactoring plan

1. Move the entire code block inside the `if (!bConsiderTouch)` to a new function `_startTangentsConsiderTouch`
    1. Replace the code with a call to 
       `_startTangentsConsiderTouch(icutEvent, ivertexCutteePrev, ivertexCuttee, ipartCutterPrev, ipartCutter, ivertexCutterPrev,
       ivertexCutterMinus, countPrev, cutEvents.size(), ivertexCutteeNext, ivertexCutteePlus, ipartCutterNext, ivertexCutterNext,
       countNext, segmentCutter, lineCutter, shape, tangent1, tangent0);`