package raven.chat.animation;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class AnimationFloatingButton {

    private final Animator animator;
    private boolean show;

    public AnimationFloatingButton(MigLayout layout, Component com) {
        this.animator = new Animator(300, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                int v;
                if (show) {
                    v = (int) (fraction * 50);
                } else {
                    v = (int) ((1f - fraction) * 50);
                }
                layout.setComponentConstraints(com, "pos 100%-50 100%-" + v + ",h 40,w 40");
                com.revalidate();
            }
        });
        this.animator.setResolution(1);
        this.animator.setAcceleration(.5f);
        this.animator.setDeceleration(.5f);
    }

    public void show() {
        if (!show) {
            stop();
            show = true;
            animator.start();
        }
    }

    public void hide() {
        if (show) {
            stop();
            show = false;
            animator.start();
        }
    }

    public void stop() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
    }

    public boolean isRunning() {
        return animator.isRunning();
    }
}
