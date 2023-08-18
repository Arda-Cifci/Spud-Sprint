package group19.InputHandler;

import group19.Entities.Movable;

/**
 * Moves the player left
 * 
 * @inheritDoc
 */
public class MoveLeft implements CharacterCommand {
    @Override
    public void execute(Movable movable) {
        movable.move(-4, 0);

    }
}