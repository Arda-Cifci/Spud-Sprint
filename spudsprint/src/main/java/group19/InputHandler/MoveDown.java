package group19.InputHandler;

import group19.Entities.Movable;

/**
 * Moves the player down
 * 
 * @inheritDoc
 */
public class MoveDown implements CharacterCommand {
    @Override
    public void execute(Movable movable) {
        movable.move(0, 4);
    }
}