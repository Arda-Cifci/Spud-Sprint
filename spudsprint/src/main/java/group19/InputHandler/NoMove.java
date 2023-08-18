package group19.InputHandler;

import group19.Entities.Movable;

/**
 * Don't move the player
 * 
 * @inheritDoc
 */
public class NoMove implements CharacterCommand {

    @Override
    public void execute(Movable movable) {
        movable.move(0, 0);
    }
}