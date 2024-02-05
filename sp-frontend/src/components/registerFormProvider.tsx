import React, { useState, type ChangeEvent, type FormEvent } from 'react';
import { registerProvider } from '../api/services/register';
import type { Provider } from '../api/services/user';

interface ProviderRegister extends Provider {
  salary: number;
  category: { name: string };
  rating: number;
}

const RegistrationForm= () => {
  const [provider, setProvider] = useState<ProviderRegister>({
    name: '',
    address: '',
    phone: '',
    mail: '',
    password: '',
    state: true,
    image: null,
    salary: 0,
    category: { name: '' },
    rating: 0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProvider(prevState => ({ ...prevState, [name]: value }));
  };

  const handleImageChange = (e) => {
    if (e.target.files) {
        setProvider(prevState => ({ ...prevState, image: e.target.files[0] }));
    }
};

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Aquí puedes enviar la información del proveedor al servidor
      await registerProvider(provider);
      console.log('Registro exitoso');
    } catch (error) {
      console.error('Error al registrar:', error);
      // Manejar errores, mostrar mensajes, etc.
    }
  };

    return (
        <>
        <form onSubmit={handleSubmit}>
            <label>
                Nombre:
                <input
                    type="text"
                    name="name"
                    value={provider.name}
                    onChange={handleChange} />
            </label>
            <label>
                Dirección:
                <input
                    type="text"
                    name="address"
                    value={provider.address}
                    onChange={handleChange} />
            </label>
            <label>
                Teléfono:
                <input
                    type="text"
                    name="phone"
                    value={provider.phone}
                    onChange={handleChange} />
            </label>
            <label>
                Correo electrónico:
                <input
                    type="email"
                    name="mail"
                    value={provider.mail}
                    onChange={handleChange} />
            </label>
            <label>
                Contraseña:
                <input
                    type="password"
                    name="password"
                    value={provider.password}
                    onChange={handleChange} />
            </label>
            <label>
                Salario:
                <input
                    type="number"
                    name="salary"
                    value={provider.salary}
                    onChange={handleChange} />
            </label>
            <label>
                Calificación:
                <input
                    type="file"
                    name="image"
                    value={provider.image}
                    onChange={handleImageChange} />
            </label>
            <button type="submit">Registrarse</button>
        </form>
        </>
    );
}

export default RegistrationForm;
