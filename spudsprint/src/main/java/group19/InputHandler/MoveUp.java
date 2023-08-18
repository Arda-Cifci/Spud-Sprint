package group19.InputHandler;

import group19.Entities.Movable;

/**
 * Moves the player up.
 * 
 * @inheritDoc
 */
public class MoveUp implements CharacterCommand {
    @Override
    public void execute(Movable movable) {
        movable.move(0, -4);

    }
}