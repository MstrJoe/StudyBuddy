import { BiArrowBack } from 'react-icons/bi';
import { Link } from 'react-router-dom';

export function BackButton({ to }) {
  return (
    <Link className="button button-outline" to={to}>
      <BiArrowBack />
      Back
    </Link>
  );
}
