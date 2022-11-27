package raven.chat.animation;

import java.awt.Component;
import javax.swing.JScrollPane;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class AnimationScroll {

    private final Animator animator;
    private TimingTarget target;

    public AnimationScroll(Component component) {
        this.animator = new Animator(350);
        this.animator.setResolution(0);
        this.animator.setAcceleration(.5f);
        this.animator.setDeceleration(.5f);
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void begin() {
                component.revalidate();
            }
        });
    }

    public void scrollHorizontal(JScrollPane scroll, int values) {
        stop();
        animator.removeTarget(target);
        target = new PropertySetter(scroll.getHorizontalScrollBar(), "value", scroll.getHorizontalScrollBar().getValue(), values);
        animator.addTarget(target);
        animator.start();
    }

    public void scrollVertical(JScrollPane scroll, int values) {
        stop();
        animator.removeTarget(target);
        target = new PropertySetter(scroll.getVerticalScrollBar(), "value", scroll.getVerticalScrollBar().getValue(), values);
        animator.addTarget(target);
        animator.start();
    }

    public void stop() {
        if (animator.isRunning()) {
            animator.stop();
        }
    }

    public boolean isRunning() {
        return animator.isRunning();
    }
}
