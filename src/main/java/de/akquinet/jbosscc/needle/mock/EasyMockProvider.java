package de.akquinet.jbosscc.needle.mock;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An EasyMock specific {@link MockProvider} implementation. For details, see
 * the EasyMock documentation.
 *
 * <pre>
 * Example:
 *
 * public class Test {
 *
 * 	private EasyMockProvider mockProvider = new EasyMockProvider();
 *
 * 	&#064;Test
 * 	public void test() {
 *
 * 	 UserDao userDao = mockProvider.createMock(UserDao.class);
 *
 * 	 mockProvider.replayAll();
 *
 * 	 // ... use mocks
 *
 * 	 mockProvider.verifyAll();
 * 	}
 * }
 * </pre>
 */
public class EasyMockProvider extends EasyMockSupport implements MockProvider {

	private static final Logger LOG = LoggerFactory.getLogger(EasyMockProvider.class);

	private Map<Class<?>, Object> mocks = new HashMap<Class<?>, Object>();

	/**
	 * {@inheritDoc} By default a mock with nice behavior. Skipping creation, if
	 * the type is final or primitive. For details, see the EasyMock
	 * documentation.
	 *
	 * @return the mock object or null, if the type is final or primitive.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T createMockComponent(final Class<T> type) {

		if (mocks.containsKey(type)) {

			Object mock = mocks.get(type);
			resetToNice(mock);
			return (T) mock;
		}

		if (Modifier.isFinal(type.getModifiers()) || type.isPrimitive()) {
			LOG.warn("Skipping creation of a mock : {} as it is final or primitive type.", type.getSimpleName());
			return null;
		}
		T mock = createNiceMock(type);
		mocks.put(type, mock);

		return mock;

	}

	/**
	 * Resets the given mock objects and turn them to a mock with nice behavior.
	 * For details, see the EasyMock documentation.
	 *
	 * @param mocks
	 *            the mock objects
	 */
	public void resetToNice(final Object... mocks) {
		EasyMock.resetToNice(mocks);
	}

	/**
	 * Reset the given mock object and turn them to a mock with nice behavior.
	 * For details, see the EasyMock documentation.
	 *
	 * @param mock
	 *            the mock object
	 *
	 * @return the mock object
	 */
	public Object resetToNice(final Object mock) {
		EasyMock.resetToNice(mock);
		return mock;
	}

	/**
	 * Resets the given mock objects and turn them to a mock with strict
	 * behavior. For details, see the EasyMock documentation.
	 *
	 * @param mocks
	 *            the mock objects
	 */
	public void resetToStrict(final Object... mocks) {
		EasyMock.resetToStrict(mocks);
	}

	/**
	 * Reset the given mock object and turn them to a mock with strict behavior.
	 * For details, see the EasyMock documentation.
	 *
	 * @param mock
	 *            the mock objects
	 *
	 * @return the mock object
	 */
	public Object resetToStrict(final Object mock) {
		EasyMock.resetToStrict(mock);
		return mock;
	}

	/**
	 * Resets the given mock objects and turn them to a mock with default
	 * behavior. For details, see the EasyMock documentation.
	 *
	 * @param mocks
	 *            the mock objects
	 */
	public void resetToDefault(final Object... mocks) {
		EasyMock.resetToDefault(mocks);
	}

	/**
	 * Reset the given mock object and turn them to a mock with default
	 * behavior. For details, see the EasyMock documentation.
	 *
	 * @param mock
	 *            the mock object
	 *
	 * @return the mock object
	 */
	public Object resetToDefault(final Object mock) {
		EasyMock.resetToDefault(mock);
		return mock;
	}

}
