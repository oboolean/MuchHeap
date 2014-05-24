package com.mojo;

import com.sun.tools.hat.internal.model.Snapshot;
import com.sun.tools.hat.internal.parser.PositionDataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HeapLoader {
  public Snapshot load(String name) throws IOException {
    PositionDataInputStream positionDataInputStream = new PositionDataInputStream(
        new FileInputStream(name));
    int i = positionDataInputStream.readInt();
    OriginalHProfReader reader = new OriginalHProfReader(name,
        positionDataInputStream, 1,
        true, 0);

    final Snapshot snapshot = reader.read();
    positionDataInputStream.close();
    snapshot.resolve(true);
    return snapshot;
  }
}
