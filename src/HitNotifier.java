/**
 * A HitNotifier is a game object that can notify HitListeners about hit events.
 */
public interface HitNotifier {
    /** Add hl as a listener to hit events.
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);
    /** Remove hl from the list of listeners to hit events.
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}