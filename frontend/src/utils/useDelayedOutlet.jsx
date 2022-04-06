import { useEffect, useState } from 'react';
import { useOutlet } from 'react-router-dom';
import { CSSTransition } from 'react-transition-group';

export function useDelayedOutlet(delay = 400) {
  const outlet = useOutlet();
  const [delayedOutlet, setDelayedOutlet] = useState(outlet);

  useEffect(() => {
    let timeout;

    if (!outlet) {
      timeout = setTimeout(() => {
        setDelayedOutlet(null);
      }, delay);
    } else {
      setDelayedOutlet(outlet);
    }

    return () => clearTimeout(timeout);
  }, [outlet?.key, delay]);

  return (
    <CSSTransition in={!!outlet} unmountOnExit timeout={delay} classNames="route">
      <div style={{ '--route-transition-duration': `${delay}ms` }}>{delayedOutlet}</div>
    </CSSTransition>
  );
}
