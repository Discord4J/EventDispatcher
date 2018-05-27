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

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This implements {@link java.util.concurrent.RejectedExecutionHandler} in order to run rejected jobs in
 * the current thread. This helps prevent issues with unbounded thread creation.
 *
 * @author <a href="https://github.com/rcano">Ghibli</a>
 */
public class CallerRunsPolicy implements RejectedExecutionHandler {

    private long lastNotification = 0;

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        long now = System.currentTimeMillis();
        synchronized(this)  {
            if (now - lastNotification >= 5000) {
                DispatcherUtils.DISPATCHER_LOGGER.warn("Event buffer limit exceeded, refer to the class-level javadocs for sx.blah.discord.api.events.EventDispatcher for more information.");
                lastNotification = now;
            }
        }
        if (!executor.isShutdown()) r.run();
    }

}
