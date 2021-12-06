package view;

import java.util.List;

import javax.swing.*;

import controller.Features;

public interface DungeonView {

  List<Integer> getSpecs();

  void setFeatures(Features f);
}
