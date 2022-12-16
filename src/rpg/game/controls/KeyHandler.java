package rpg.game.controls;

import rpg.game.item.Item;
import rpg.game.item.ItemType;
import rpg.game.item.armor.*;
import rpg.game.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, showDrawTime, fPressed, enterPressed,
            spellKeyPressed, godMode = false;
    private GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_W) {
            gamePanel.ui.commandNum--;
            if (gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gamePanel.ui.commandNum++;
            if (gamePanel.ui.commandNum > 2) {
                gamePanel.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.commandNum == 0) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.music.playMusic(0);
            } else if (gamePanel.ui.commandNum == 1) {

            } else if (gamePanel.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            gamePanel.player.casting = true;
        }
        if (code == KeyEvent.VK_T) {
            if (showDrawTime) {
                showDrawTime = false;
            } else {
                showDrawTime = true;
            }
        }
        if (code == KeyEvent.VK_G) {
            if (godMode) {
                gamePanel.player.calculateStats();
                godMode = false;
            } else {
                gamePanel.player.hp = gamePanel.player.maxHp;
                gamePanel.player.mana = gamePanel.player.maxMana;
                gamePanel.player.damage = 1000;
                godMode = true;
            }
        }
        if (code == KeyEvent.VK_P) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.pauseState;
        }
        if (code == KeyEvent.VK_F) {
            fPressed = true;
        }
        if (code == KeyEvent.VK_E) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.inventoryState;
        }
        if (code == KeyEvent.VK_C) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.characterState;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.optionsState;
        }
        if (code == KeyEvent.VK_R) {
            gamePanel.sound.playSound(5);
            gamePanel.player.hp = 0;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.playState;
        }
        if (code == KeyEvent.VK_E) {
            gamePanel.player.exp = gamePanel.player.nextLvlExp;
            gamePanel.player.checkLevel();
        }
    }

    public void inventoryState(int code) {
        if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.playState;
        }
//        if (code == KeyEvent.VK_W) {
//            gamePanel.sound.playSound(5);
//            if (gamePanel.ui.slotRow != 0) {
//                gamePanel.ui.slotRow--;
//            }
//        }
//        if (code == KeyEvent.VK_S) {
//            gamePanel.sound.playSound(5);
//            if (gamePanel.ui.slotRow != 3) {
//                gamePanel.ui.slotRow++;
//            }
//        }
//        if (code == KeyEvent.VK_A) {
//            gamePanel.sound.playSound(5);
//            if (gamePanel.ui.slotCol != 0) {
//                gamePanel.ui.slotCol--;
//            }
//        }
//        if (code == KeyEvent.VK_D) {
//            gamePanel.sound.playSound(5);
//            if (gamePanel.ui.slotCol != 4) {
//                gamePanel.ui.slotCol++;
//            }
//        }
//        if (code == KeyEvent.VK_ENTER) {
//            int itemIndex = gamePanel.ui.getItemIndexOfSlot();
//            if (itemIndex < gamePanel.player.inventory.size()) {
//                gamePanel.sound.playSound(5);
//                Item item = gamePanel.player.inventory.get(itemIndex);
//                if (item.itemType == ItemType.ARMOR) {
//                    if (((Armor) item).equipped) {
//                        if (((Armor) item).armorType == ArmorType.BOOTS) {
//                            gamePanel.player.boots = null;
//                        } else if (((Armor) item).armorType == ArmorType.BREASTPLATE) {
//                            gamePanel.player.breastplate = null;
//                        } else if (((Armor) item).armorType == ArmorType.HELMET) {
//                            gamePanel.player.helmet = null;
//                        } else if (((Armor) item).armorType == ArmorType.NECKLACE) {
//                            gamePanel.player.necklace = null;
//                        } else if (((Armor) item).armorType == ArmorType.RING) {
//                            gamePanel.player.ring = null;
//                        }
//                        ((Armor) item).equipped = false;
//                        gamePanel.player.calculateStats();
//                    } else {
//                        if (((Armor) item).armorType == ArmorType.BOOTS) {
//                            if (gamePanel.player.boots != null)
//                                gamePanel.player.boots.equipped = false;
//                            gamePanel.player.boots = ((Boots) item);
//                        } else if (((Armor) item).armorType == ArmorType.BREASTPLATE) {
//                            if (gamePanel.player.breastplate != null)
//                                gamePanel.player.breastplate.equipped = false;
//                            gamePanel.player.breastplate = ((Breastplate) item);
//                        } else if (((Armor) item).armorType == ArmorType.GLOVES) {
//                            if (gamePanel.player.gloves != null)
//                                gamePanel.player.gloves.equipped = false;
//                            gamePanel.player.gloves = ((Gloves) item);
//                        } else if (((Armor) item).armorType == ArmorType.HELMET) {
//                            if (gamePanel.player.helmet != null)
//                                gamePanel.player.helmet.equipped = false;
//                            gamePanel.player.helmet = ((Helmet) item);
//                        } else if (((Armor) item).armorType == ArmorType.NECKLACE) {
//                            if (gamePanel.player.necklace != null)
//                                gamePanel.player.necklace.equipped = false;
//                            gamePanel.player.necklace = ((Necklace) item);
//                        } else if (((Armor) item).armorType == ArmorType.RING) {
//                            if (gamePanel.player.ring != null)
//                                gamePanel.player.ring.equipped = false;
//                            gamePanel.player.ring = ((Ring) item);
//                        }
//                        ((Armor) item).equipped = true;
//                        gamePanel.player.calculateStats();
//                    }
//                } else if (item.itemType == ItemType.USABLE) {
//                    item.use(gamePanel);
//                    gamePanel.player.inventory.remove(itemIndex);
//                }
//            }
//        }
    }

    private void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.sound.playSound(5);
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.commandNum = 0;
        }
        int maxCommandNum = switch (gamePanel.ui.subState) {
            case 0 -> 5;
            case 3 -> 1;
            default -> 0;
        };

        if (code == KeyEvent.VK_W) {
            gamePanel.sound.playSound(5);
            gamePanel.ui.commandNum--;
            if (gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gamePanel.sound.playSound(5);
            gamePanel.ui.commandNum++;
            if (gamePanel.ui.commandNum > maxCommandNum) {
                gamePanel.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.sound.playSound(5);
            enterPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.subState == 0) {
                if (gamePanel.ui.commandNum == 1 && gamePanel.music.volumeScale > 0) {
                    gamePanel.music.volumeScale--;
                    gamePanel.music.checkVolume();
                }
            }
        }
        if (code == KeyEvent.VK_A) {
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.subState == 0) {
                if (gamePanel.ui.commandNum == 2 && gamePanel.sound.volumeScale > 0) {
                    gamePanel.sound.volumeScale--;
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.subState == 0) {
                if (gamePanel.ui.commandNum == 1 && gamePanel.music.volumeScale < 5) {
                    gamePanel.music.volumeScale++;
                    gamePanel.music.checkVolume();
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.subState == 0) {
                if (gamePanel.ui.commandNum == 2 && gamePanel.sound.volumeScale < 5) {
                    gamePanel.sound.volumeScale++;
                }
            }
        }
    }

    private void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gamePanel.ui.commandNum --;
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_S) {
            gamePanel.ui.commandNum ++;
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.commandNum > 1) {
                gamePanel.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            gamePanel.sound.playSound(5);
            if (gamePanel.ui.commandNum == 0) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.retry();
            }
            else if (gamePanel.ui.commandNum == 1) {
                gamePanel.gameState = gamePanel.titleState;
                gamePanel.restart();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE
        if (gamePanel.gameState == gamePanel.titleState) {
            titleState(code);
        }

        //GAME
        else if (gamePanel.gameState == gamePanel.playState) {
            playState(code);
        }
        //PAUSE
        else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(code);
        }
        //DIALOGUE
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            dialogueState(code);
        }
        //CHARACTER
        else if (gamePanel.gameState == gamePanel.inventoryState) {
            inventoryState(code);
        }
        else if (gamePanel.gameState == gamePanel.characterState) {
            characterState(code);
        }
        else if (gamePanel.gameState == gamePanel.optionsState) {
            optionsState(code);
        }
        else if (gamePanel.gameState == gamePanel.gameOverState) {
            gameOverState(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            spellKeyPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            fPressed = false;
        }
    }
}
