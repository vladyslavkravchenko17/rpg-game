package rpg.game.controls;

import rpg.game.UI.InventorySlotType;
import rpg.game.item.Item;
import rpg.game.item.ItemType;
import rpg.game.item.armor.*;
import rpg.game.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    GamePanel gamePanel;
    public double mousePositionX, mousePositionY;
    private boolean mouseIsAvailable = true;
    private int mouseIsAvailableCounter;
    private boolean doubleClick, click;
    private int doubleClickTimer = 0;

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void update() {
        if (!mouseIsAvailable && mouseIsAvailableCounter != 5) {
            mouseIsAvailableCounter++;
        } else {
            mouseIsAvailable = true;
            mouseIsAvailableCounter = 0;
        }
        if (click) {
            doubleClickTimer++;
            if (doubleClickTimer == 30) {
                doubleClickTimer = 0;
                click = false;
            }
        }
    }

    private void playState(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            gamePanel.player.attacking = true;
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            gamePanel.player.casting = true;
        }
    }

    private void inventoryState(MouseEvent e) {

        if (mouseIsAvailable) {
            mouseIsAvailable = false;
            if (!gamePanel.ui.dragItem) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1 && gamePanel.ui.hasActiveSlot) {
//                    gamePanel.sound.playSound(5);
//                    if (gamePanel.ui.inventorySlotType == InventorySlotType.INVENTORY) {
//                        int itemIndex = gamePanel.ui.getItemIndexOfSlot();
//                        if (itemIndex < gamePanel.player.inventory.size()) {
//                            Item item = gamePanel.player.inventory.get(itemIndex);
//                            if (item.itemType == ItemType.ARMOR) {
//                                if (((Armor) item).armorType == ArmorType.BOOTS) {
//                                    if (gamePanel.player.boots != null) {
//                                        gamePanel.player.boots.equipped = false;
//                                        gamePanel.player.inventory.add(gamePanel.player.boots);
//                                    }
//                                    gamePanel.player.boots = ((Boots) item);
//                                } else if (((Armor) item).armorType == ArmorType.BREASTPLATE) {
//                                    if (gamePanel.player.breastplate != null) {
//                                        gamePanel.player.breastplate.equipped = false;
//                                        gamePanel.player.inventory.add(gamePanel.player.breastplate);
//                                    }
//                                    gamePanel.player.breastplate = ((Breastplate) item);
//                                } else if (((Armor) item).armorType == ArmorType.GLOVES) {
//                                    if (gamePanel.player.gloves != null) {
//                                        gamePanel.player.gloves.equipped = false;
//                                        gamePanel.player.inventory.add(gamePanel.player.gloves);
//                                    }
//                                    gamePanel.player.gloves = ((Gloves) item);
//                                } else if (((Armor) item).armorType == ArmorType.HELMET) {
//                                    if (gamePanel.player.helmet != null) {
//                                        gamePanel.player.helmet.equipped = false;
//                                        gamePanel.player.inventory.add(gamePanel.player.helmet);
//                                    }
//                                    gamePanel.player.helmet = ((Helmet) item);
//                                } else if (((Armor) item).armorType == ArmorType.NECKLACE) {
//                                    if (gamePanel.player.necklace != null) {
//                                        gamePanel.player.necklace.equipped = false;
//                                        gamePanel.player.inventory.add(gamePanel.player.necklace);
//                                    }
//                                    gamePanel.player.necklace = ((Necklace) item);
//                                } else if (((Armor) item).armorType == ArmorType.RING) {
//                                    if (gamePanel.player.ring != null) {
//                                        gamePanel.player.ring.equipped = false;
//                                        gamePanel.player.inventory.add(gamePanel.player.ring);
//                                    }
//                                    gamePanel.player.ring = ((Ring) item);
//                                }
//                                ((Armor) item).equipped = true;
//                                gamePanel.player.inventory.remove(item);
//                                gamePanel.player.calculateStats();
//                            } else if (item.itemType == ItemType.USABLE) {
//                                item.use(gamePanel);
//                                gamePanel.player.inventory.remove(itemIndex);
//                            }
//                        }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.HELMET) {
//                        if (gamePanel.player.helmet != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.helmet);
//                            gamePanel.player.helmet = null;
//                            gamePanel.player.calculateStats();
//                        }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.BREASTPLATE) {
//                        if (gamePanel.player.breastplate != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.breastplate);
//                            gamePanel.player.breastplate = null;
//                            gamePanel.player.calculateStats();
//                        }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.GLOVES) {
//                        if (gamePanel.player.gloves != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.gloves);
//                            gamePanel.player.gloves = null;
//                            gamePanel.player.calculateStats();
//                        }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.BOOTS) {
//                        if (gamePanel.player.boots != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.boots);
//                            gamePanel.player.boots = null;
//                            gamePanel.player.calculateStats();
//                        }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.WEAPON) {
////                if (gamePanel.player.boots != null) {
////                    gamePanel.player.inventory.add(gamePanel.player.boots);
////                    gamePanel.player.boots = null;
////                    gamePanel.player.calculateStats();
////                }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.NECKLACE) {
//                        if (gamePanel.player.necklace != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.necklace);
//                            gamePanel.player.necklace = null;
//                            gamePanel.player.calculateStats();
//                        }
//                    } else if (gamePanel.ui.inventorySlotType == InventorySlotType.RING) {
//                        if (gamePanel.player.ring != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.ring);
//                            gamePanel.player.ring = null;
//                            gamePanel.player.calculateStats();
//                        }
//                    }
                }

                else if (SwingUtilities.isLeftMouseButton(e)) {
                    if (doubleClickTimer == 0) {
                        doubleClickTimer = 0;
                        gamePanel.ui.dragItem = true;
                        if (gamePanel.ui.inventorySlotType == InventorySlotType.INVENTORY) {
                            int itemIndex = gamePanel.ui.getItemIndexOfSlot();
                            if (itemIndex < gamePanel.player.inventory.size()) {
                                Item item = gamePanel.player.inventory.get(itemIndex);
                                gamePanel.ui.draggingItem = item;
                                gamePanel.player.inventory.remove(item);
                            }
                        }
                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.HELMET) {
                            Item item = gamePanel.player.helmet;
                            gamePanel.ui.draggingItem = item;
                            gamePanel.player.helmet = null;
                        }
                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.BREASTPLATE) {
                            Item item = gamePanel.player.breastplate;
                            gamePanel.ui.draggingItem = item;
                            gamePanel.player.breastplate = null;
                        }
                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.GLOVES) {
                            Item item = gamePanel.player.gloves;
                            gamePanel.ui.draggingItem = item;
                            gamePanel.player.gloves = null;
                        }
                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.BOOTS) {
                            Item item = gamePanel.player.boots;
                            gamePanel.ui.draggingItem = item;
                            gamePanel.player.boots = null;
                        }
//                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.WEAPON) {
//                            Item item = gamePanel.player.helmet;
//                            gamePanel.ui.draggingItem = item;
//                            gamePanel.player.helmet = null;
//                        }
                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.NECKLACE) {
                            Item item = gamePanel.player.necklace;
                            gamePanel.ui.draggingItem = item;
                            gamePanel.player.necklace = null;
                        }
                        else if (gamePanel.ui.inventorySlotType == InventorySlotType.RING) {
                            Item item = gamePanel.player.ring;
                            gamePanel.ui.draggingItem = item;
                            gamePanel.player.ring = null;
                        }
                        if (gamePanel.ui.draggingItem == null) {
                            gamePanel.ui.dragItem = false;
                        }
                    } else {
                        doubleClickTimer++;
                    }
                }
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                mouseIsAvailable = false;
                Item item = gamePanel.ui.draggingItem;
                boolean draggedItem = false;
                if (gamePanel.ui.inventorySlotType == InventorySlotType.INVENTORY) {
                    gamePanel.player.inventory.add(gamePanel.ui.draggingItem);
                    draggedItem = true;
                } else if (gamePanel.ui.inventorySlotType == InventorySlotType.HELMET
                        && item.itemType == ItemType.ARMOR
                        && ((((Armor) item)).armorType == ArmorType.HELMET)) {
                    if (gamePanel.player.helmet != null) {
                        gamePanel.player.inventory.add(gamePanel.player.helmet);
                    }
                    gamePanel.player.helmet = (((Helmet) item));
                    gamePanel.player.calculateStats();
                    draggedItem = true;

                } else if (gamePanel.ui.inventorySlotType == InventorySlotType.BREASTPLATE
                        && item.itemType == ItemType.ARMOR
                        && ((((Armor) item)).armorType == ArmorType.BREASTPLATE)) {
                    if (gamePanel.player.breastplate != null) {
                        gamePanel.player.inventory.add(gamePanel.player.breastplate);
                    }
                    gamePanel.player.breastplate = (((Breastplate) item));
                    gamePanel.player.calculateStats();
                    draggedItem = true;
                } else if (gamePanel.ui.inventorySlotType == InventorySlotType.GLOVES
                        && item.itemType == ItemType.ARMOR
                        && ((((Armor) item)).armorType == ArmorType.GLOVES)) {
                    if (gamePanel.player.gloves != null) {
                        gamePanel.player.inventory.add(gamePanel.player.gloves);
                    }
                    gamePanel.player.gloves = (((Gloves) item));
                    gamePanel.player.calculateStats();
                    draggedItem = true;
                } else if (gamePanel.ui.inventorySlotType == InventorySlotType.BOOTS
                        && item.itemType == ItemType.ARMOR
                        && ((((Armor) item)).armorType == ArmorType.BOOTS)) {
                    if (gamePanel.player.boots != null) {
                        gamePanel.player.inventory.add(gamePanel.player.boots);
                    }
                    gamePanel.player.boots = (((Boots) item));
                    gamePanel.player.calculateStats();
                    draggedItem = true;
                }
//                    else if (gamePanel.ui.inventorySlotType == InventorySlotType.WEAPON
//                            && item.itemType == ItemType.ARMOR
//                            && ((((Armor) item)).armorType == ArmorType.HELMET)) {
//                        if (gamePanel.player.helmet != null) {
//                            gamePanel.player.inventory.add(gamePanel.player.helmet);
//            }
//                            gamePanel.player.helmet = (((Helmet) item));
//                            gamePanel.player.calculateStats();
//                            draggedItem = true;
//                    }
                else if (gamePanel.ui.inventorySlotType == InventorySlotType.NECKLACE
                        && item.itemType == ItemType.ARMOR
                        && ((((Armor) item)).armorType == ArmorType.NECKLACE)) {
                    if (gamePanel.player.necklace != null) {
                        gamePanel.player.inventory.add(gamePanel.player.necklace);
                    }
                        gamePanel.player.necklace = (((Necklace) item));
                        gamePanel.player.calculateStats();
                        draggedItem = true;
                } else if (gamePanel.ui.inventorySlotType == InventorySlotType.RING
                        && item.itemType == ItemType.ARMOR
                        && ((((Armor) item)).armorType == ArmorType.RING)) {
                    if (gamePanel.player.ring != null) {
                        gamePanel.player.inventory.add(gamePanel.player.ring);
                    }
                        gamePanel.player.ring = (((Ring) item));
                        gamePanel.player.calculateStats();
                        draggedItem = true;
                }


                if (draggedItem) {
                    gamePanel.ui.dragItem = false;
                    gamePanel.ui.draggingItem = null;
                }
            }
        }
    }

    private void switchGameState(MouseEvent e) {
        if (gamePanel.gameState == gamePanel.playState) {
            playState(e);
        } else if (gamePanel.gameState == gamePanel.inventoryState) {
            inventoryState(e);
        }
    }

    public void checkMousePosition() {
        mousePositionX = MouseInfo.getPointerInfo().getLocation().x;
        mousePositionY = MouseInfo.getPointerInfo().getLocation().y;

        mousePositionX = mousePositionX * gamePanel.screenRelationX;
        mousePositionY = mousePositionY * gamePanel.screenRelationY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switchGameState(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switchGameState(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switchGameState(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switchGameState(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switchGameState(e);
    }
}
