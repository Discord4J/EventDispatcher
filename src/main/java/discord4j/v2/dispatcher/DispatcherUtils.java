/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.v2.dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * A set of utilities required for {@link discord4j.v2.dispatcher.EventDispatcher}.
 *
 * @author <a href="https://github.com/austinv11">Austin</a>
 */
public class DispatcherUtils {

    public static final Logger DISPATCHER_LOGGER = LoggerFactory.getLogger("EventDispatcher");

    /**
     * Creates a {@link ThreadFactory} which produces threads which run as daemons.
     *
     * @return The new daemon thread factory.
     */
    public static ThreadFactory createDaemonThreadFactory() {
        return createDaemonThreadFactory(null);
    }

    /**
     * This creates a {@link ThreadFactory} which produces threads which run as daemons.
     *
     * @param threadName The name of threads created by the returned factory.
     * @return The new daemon thread factory.
     */
    public static ThreadFactory createDaemonThreadFactory(String threadName) {
        return (runnable) -> { //Ensures all threads are daemons
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            if (threadName != null)
                thread.setName(threadName);
            thread.setDaemon(true);
            return thread;
        };
    }
}
