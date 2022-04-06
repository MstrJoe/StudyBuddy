import './Drawer.scss';

export function Drawer({ children, onClose }) {
  return (
    <>
      <div className="drawer-overlay" onClick={onClose} role="button" tabIndex={-1} />
      <div className="drawer-content">{children}</div>
    </>
  );
}
